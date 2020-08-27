import { Component, OnInit } from '@angular/core';
import { TkStorageService } from 'src/app/services/tk-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private roles: string [];
  showAdmin = false;
  showEmployee= false;
  username: string;
  isLoggedIn = false;
  constructor(private tokenStorageService: TkStorageService, private router: Router){

  }
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    
        if (this.isLoggedIn) {
          const user = this.tokenStorageService.getUser();
          this.roles = user.roles;
    
          this.showAdmin = this.roles.includes('ROLE_ADMIN');
          this.showEmployee = this.roles.includes('ROLE_EMPLOYEE');

    
          this.username = user.username;
        }
      }
    
      logout(): void {
        this.tokenStorageService.signOut();
        this.router.navigate(['login']);
      }

}
