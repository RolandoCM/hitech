import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';


const API_URL= 'http://localhost:8080/api/hitech/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService { 
  
  constructor(private http: HttpClient) {
   }
  
   getEmployee(username): Observable<any> {
     console.log(username);
    return this.http.get(API_URL+'employee?username='+ encodeURIComponent( username),httpOptions);
   }

   updateEmployee(data): Observable<any> {
     return this.http.put(API_URL+'employee', 
     JSON.parse(data), 
     httpOptions);
   }
   allEmployees(): Observable<any> {
     return this.http.get(API_URL+'employees',
     httpOptions);
   }

   delete(code): Observable<any> {
     return this.http.post(API_URL+'disable', 
     code
     , httpOptions);
   }
  
}
