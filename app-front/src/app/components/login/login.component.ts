import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { TkStorageService } from 'src/app/services/tk-storage.service'
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed= false;
  errorMessage= '';
  roles: string[]= [];
  
  constructor(private authService: AuthService, private tokenStorage: TkStorageService, public router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data=> { 
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoginFailed = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.redirect();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  redirect(): void {
    this.router.navigate(['home']);
  }

}
