import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {User} from '../dto/User';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(
    private http: HttpClient
  ) {
  }

  get(token: string): Observable<User> {
    return this.http.get<User>(API_URL + 'user/get', {
      headers: {Authorization: token.toString()}
    }
  );
  }
}
