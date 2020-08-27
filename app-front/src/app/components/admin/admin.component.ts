import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  employees: any;
  constructor(private employeesService:  EmployeeService) { }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  getAllEmployees(){
    this.employeesService.allEmployees().subscribe(
      data=>{
        console.log(data.employees);
        this.employees = data.employees;
      },
      err => {
        console.log(err);
      }
    );
  }
  delete(code: string): void {
    const employee = this.employees.find(x => x.code === code);

    this.employeesService.delete(code).subscribe(
      data=> {
        this.employees = this.employees.filter(x => x.code !== code);
      },
      err => {
        console.log(err);
      }
    );
  }

}
