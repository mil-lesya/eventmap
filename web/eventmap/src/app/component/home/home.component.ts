import {Component, OnInit} from '@angular/core';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';
import {UserService} from '../../service/user.service';
import {User} from '../../dto/User';
import {MarkService} from '../../service/mark.service';
import {Marker} from '../../dto/Marker';
import {Event} from '../../dto/Event';
import {EventService} from '../../service/event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  lat = 43.879078;
  lng = -103.4615581;
  selectedMarker: Marker = new Marker();
  markers = [];
  user: User;
  mark: Marker = new Marker();
  token: string;
  newEvent: Event = new Event();
  delete = false;
  createEvent = false;
  existEvent = [];

  constructor(
    private tokenProviderService: TokenProviderService,
    private userService: UserService,
    private markService: MarkService,
    private eventService: EventService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.markService.getMarkers().subscribe(markers => {
      this.markers = markers;
      this.tokenProviderService.token.subscribe(token => {
        this.token = token;
        this.userService.get(token).subscribe(user => {
          this.user = user;
        });
      });
    });
  }


  addMarker(lat: number, lng: number) {
    if (!this.user) {
      return;
    }
    console.log('add');
    this.markers.push({lat, lng, alpha: 0.4});
    this.mark.latitude = lat;
    this.mark.longitude = lng;
    this.mark.user = this.user;
    this.createEvent = true;
  }


  createNewEvent() {
    this.createEvent = false;
    this.eventService.createEvent(this.newEvent, this.token).subscribe(event => {
      console.log('create');
      console.log(event);
      this.mark.event = JSON.parse(event.toString());
      this.markService.save(this.mark, this.token).subscribe(() => {
        this.markService.getMarkers().subscribe(markers => {
          this.markers = markers;
        });
      });
    });
  }

  deleteMark() {
    if (this.delete) {
      this.markService.deleteMarker(this.token, this.mark).subscribe(() => {
        console.log('delete');
      });
    }
  }

  selectMarker(event) {
    console.log('select');
    this.selectedMarker.latitude = event.latitude;
    this.selectedMarker.longitude = event.longitude;
  }
}

