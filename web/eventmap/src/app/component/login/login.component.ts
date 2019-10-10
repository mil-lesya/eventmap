import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';
import {AuthUserService} from '../../service/auth.user.service';
import {AuthUser} from '../../dto/AuthUser';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authUser: AuthUser = new AuthUser();

  constructor(
    private authUserService: AuthUserService,
    private router: Router,
    private tokenProviderService: TokenProviderService,
    private errorService: ErrorService
  ) {
  }

   ngOnInit() {
  }

  authenticate() {
    this.authUserService.authenticate(this.authUser).subscribe(resp => {
        const token = resp.headers.get('Authorization');
        this.tokenProviderService.setToken(token);
        console.log(this.authUser);
        console.log('set token', token);
        localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

        this.router.navigate(['/home'], {replaceUrl: true});
      },
      er => this.errorService.raise(er));
  }


}
