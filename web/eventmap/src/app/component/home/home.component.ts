import {Component, OnInit} from '@angular/core';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';
import {UserService} from '../../service/user.service';
import {User} from '../../dto/User';
import {MarkService} from '../../service/mark.service';
import {Marker} from '../../dto/Marker';
import {Event} from '../../dto/Event';
import {SelectedMark} from '../../dto/SelectedMark';
import {EventService} from '../../service/event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  lat = 43.879078;
  lng = -103.4615581;
  selectedMarker: SelectedMark = new SelectedMark();
  markers = [];
  user: User;
  mark: Marker = new Marker();
  token: string;
  newEvent: Event = new Event();
  delete = false;
  createEvent = false;
  marker: Marker = new Marker();
  existEvent: Event = new Event();

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
    this.markers.push({lat, lng, alpha: 0.4}
    );
    this.mark.latitude = lat;
    this.mark.longitude = lng;
    this.mark.user = this.user;
    this.markService.save(this.mark, this.token).subscribe(() => {
      this.createEvent = true;
      console.log('save');
    });
  }

  selectMarker(event) {

    console.log('select');
    this.selectedMarker.latitude = event.latitude;
    this.selectedMarker.longitude = event.longitude;
    this.markService.getMarker(this.token, this.selectedMarker).subscribe(mark => {
      console.log('get marker');
      this.marker = mark;
      this.eventService.getEvent(this.token, mark).subscribe(existEvent => {
        this.existEvent = JSON.parse(existEvent.toString());
        console.log(this.existEvent);
      });
    });
  }

  createNewEvent() {
    this.createEvent = false;
    this.newEvent.marker = this.marker;
    this.eventService.createEvent(this.newEvent, this.token).subscribe(() => {
      console.log('create');
      this.markService.getMarkers().subscribe(markers => {
        this.markers = markers;
      });
    });
  }

  deleteMark() {
    if (this.delete) {
      this.markService.deleteMarker(this.token, this.marker).subscribe(() => {
        console.log('delete');
      });
    }
  }

}


