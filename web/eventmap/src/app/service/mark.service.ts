import {Injectable} from '@angular/core';
import {Marker} from '../dto/Marker';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MarkService {


  constructor(
    private http: HttpClient
  ) {
  }

  save(mark: Marker, token: string): Observable<Marker> {
    return this.http.post<Marker>(API_URL + 'marker/save', mark, {
      responseType: 'text' as 'json',
      headers: {Authorization: token.toString()}
    });
  }

  getMarkers(): Observable<Marker[]> {
    return this.http.get<Marker[]>(API_URL + 'marker/get/all');
  }

  getMarker(selectedMark): Observable<Marker> {
    return this.http.post<Marker>(API_URL + 'marker/get', selectedMark,{
      responseType: 'text' as 'json',
    });
  }

  deleteMarker(token: string, marker: Marker): Observable<void> {
    return this.http.post<void>(API_URL + 'delete', marker, {
      headers: {Authorization: token.toString()},
    });
  }

}
