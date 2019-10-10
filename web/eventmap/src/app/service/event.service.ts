import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Event} from '../dto/Event';
import {API_URL} from '../../global';
import {Marker} from '../dto/Marker';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(
    private http: HttpClient
  ) {
  }

  createEvent(newEvent: Event, token: string): Observable<Event>  {
    return this.http.post<Event>(API_URL + 'event/create', newEvent, {
      responseType: 'text' as 'json',
      headers: {Authorization: token.toString()}
    });
  }

  getEvent(mark: Marker): Observable<Event> {
    return this.http.post<Event>(API_URL + 'event/get', mark, {
      responseType: 'text' as 'json'
    });
  }
}
