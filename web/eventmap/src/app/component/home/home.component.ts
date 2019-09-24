import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';
import {UserService} from '../../service/user.service';
import {User} from '../../dto/User';
import {MarkService} from '../../service/mark.service';
import {Mark} from '../../dto/Mark';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  lat = 43.879078;
  lng = -103.4615581;
  selectedMarker;
  markers = [];
  user: User;
  mark: Mark = new Mark();
  token: string;

  constructor(
    private tokenProviderService: TokenProviderService,
    private userService: UserService,
    private markService: MarkService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      this.token = token;
      this.userService.get(token).subscribe(user => {
        this.user = user;
        console.log('marks');
        this.markService.getMarks(token).subscribe(markers => {
          this.markers = markers;
          console.log(markers);
        });
      });
    });
  }


  addMarker(lat: number, lng: number) {
    this.markers.push({lat, lng, alpha: 0.4}
    );
    this.mark.latitude = lat;
    this.mark.longitude = lng;
    this.mark.user = this.user;
    this.markService.save(this.mark, this.token).subscribe(() => {
      console.log('save');
    });
  }

  selectMarker(event) {
    this.selectedMarker = {
      lat: event.latitude,
      lng: event.longitude
    };

  }
}


