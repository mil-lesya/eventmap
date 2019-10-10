import {Injectable} from '@angular/core';
import {AuthUser} from '../dto/AuthUser';
import {API_URL} from '../../global';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class AuthUserService {

  constructor(
    private http: HttpClient
  ) {
  }

  authenticate(authUser: AuthUser): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(API_URL + 'login', authUser, {observe: 'response'});
  }
}
