import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';


const AUTH_API='http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API+'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  signup(employee): Observable<any> {
    return this.http.post(AUTH_API+'signup', 
      employee, httpOptions);
  }
}

