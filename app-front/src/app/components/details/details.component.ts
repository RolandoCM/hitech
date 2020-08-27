import { Component, OnInit } from '@angular/core';
import { TkStorageService } from 'src/app/services/tk-storage.service';
import { EmployeeService } from 'src/app/services/employee.service';
import { FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private employeeService: EmployeeService,
      private tokenStorage: TkStorageService
  ) {}

  ngOnInit() {
      // password not required in edit mode
      //const passwordValidators = [Validators.minLength(6)];

      this.form = this.formBuilder.group({
          id: new FormControl({value: '', disabled: true}, Validators.required),
          username: new FormControl({ value:'', disabled: true }, Validators.required),
          email: new FormControl('', Validators.required),
          name: new FormControl('', Validators.required),
          code: new FormControl({ value:'', disabled: true }, Validators.required),
          city: new FormControl('', Validators.required),
          branch: new FormControl('', Validators.required),
          profession: new FormControl('', Validators.required)
      });
      this.employeeService.getEmployee(this.tokenStorage.getUser().username).subscribe(
        data => {
          this.f.id.setValue(data.employee.id);
          this.f.username.setValue(data.employee.username);
          this.f.email.setValue(data.employee.email);
          this.f.code.setValue(data.employee.code);
          this.f.name.setValue(data.employee.name);
          this.f.city.setValue(data.employee.city);
          this.f.branch.setValue(data.employee.branch);
          this.f.profession.setValue(data.employee.profession);
        },err =>{
          console.log(err);
        });
                  
  

  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    console.log(this.f.name.touched);
      this.submitted = true;
      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }
      this.loading = true;
      const employee: Employee = {
        id: this.f.id.value,
        name: this.f.name.value,
        username: this.f.username.value,
        email: this.f.email.value,
        code: this.f.code.value,
        city: this.f.city.value,
        profession: this.f.profession.value,
        branch: this.f.branch.value,
        password:''
      }
      this.employeeService.updateEmployee(JSON.stringify(employee)).subscribe(
        data=> {
          this.loading = false;
        },
        err =>{
          console.log(err);
        }
      );
  }
}