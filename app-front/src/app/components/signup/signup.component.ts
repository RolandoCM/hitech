import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service'
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  form: FormGroup;
  loading = false;
  submitted = false;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage= '';

  constructor(private authService: AuthService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      id: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      code: new FormControl('' , Validators.required),
      city: new FormControl('', Validators.required),
      branch: new FormControl('', Validators.required),
      profession: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
  });
  }
  onSubmit(): void {
    const employee: Employee = {
      id: this.f.id.value,
      name: this.f.name.value,
      username: this.f.username.value,
      email: this.f.email.value,
      code: this.f.code.value,
      city: this.f.city.value,
      profession: this.f.profession.value,
      branch: this.f.branch.value,
      password: this.f.password.value
    }
    
    this.authService.signup(employee).subscribe(
      data=> {
        this.isSuccessful=true;
        this.isSignUpFailed=false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed= true;
      }
    );
  }
    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

}
