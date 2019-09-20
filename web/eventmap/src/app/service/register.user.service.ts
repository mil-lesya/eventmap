import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RegisterUser} from '../dto/RegisterUser';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';

@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  constructor(private http: HttpClient) { }

  register(registerUser: RegisterUser): Observable<void> {
    return this.http.post<void>(API_URL + 'register', registerUser);
  }
}
