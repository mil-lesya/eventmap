import {Component, OnInit} from '@angular/core';
import {RegisterUser} from '../../dto/RegisterUser';
import {RegisterUserService} from '../../service/register.user.service';
import {Router} from '@angular/router';
import {ErrorService} from '../../service/error.service';
import {VerifiableRegisterUser} from '../../dto/VerifiableRegisterUser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  verifiableRegisterUser: VerifiableRegisterUser = new VerifiableRegisterUser();

  constructor(
    private registerUserService: RegisterUserService,
    private router: Router,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
  }

  register() {
    if (this.verifiableRegisterUser.password !== this.verifiableRegisterUser.confirmPassword) {
      alert('invalid repeated password');
      return;
    }

    const registerUser = new RegisterUser(
      this.verifiableRegisterUser.name,
      this.verifiableRegisterUser.surname,
      this.verifiableRegisterUser.email,
      this.verifiableRegisterUser.password
    );

    this.registerUserService.register(registerUser).subscribe(() => {
      this.router.navigate(['/login'], {replaceUrl: true});
    }, err => this.errorService.raise(err));
  }

}
