import { Injectable } from '@angular/core';
import {AuthUser} from '../dto/AuthUser';
import {API_URL} from '../../global';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class AuthUserService {

  constructor(
    private http: HttpClient
  ) { }

  authenticate(authUser: AuthUser): Observable<string> {
    return this.http.post<string>(API_URL + 'login', authUser, {responseType: 'text' as 'json'});
  }
}
