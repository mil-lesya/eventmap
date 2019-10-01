import {Injectable} from '@angular/core';
import {Marker} from '../dto/Marker';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {HttpClient} from '@angular/common/http';
import {User} from '../dto/User';

@Injectable({
  providedIn: 'root'
})
export class MarkService {


  constructor(
    private http: HttpClient
  ) {
  }

  save(mark: Marker, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'marker/save', mark, {
      responseType: 'text' as 'json',
      headers: {token: token.toString()}
    });
  }

  getMarkers(): Observable<Marker[]> {
    return this.http.get<Marker[]>(API_URL + 'marker/get/all');
  }

  getMarker(token: string, selectedMark): Observable<Marker> {
    return this.http.post<Marker>(API_URL + 'marker/get', selectedMark, {
      headers: {token: token.toString()},
    });
  }

  deleteMarker(token: string, marker: Marker): Observable<void> {
    return this.http.post<void>(API_URL + 'delete', marker, {
      headers: {token: token.toString()},
    });
  }

}
