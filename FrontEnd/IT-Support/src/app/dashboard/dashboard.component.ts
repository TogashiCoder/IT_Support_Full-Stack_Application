import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  sidebarOpen = true;
  userRole!: string;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.userRole = this.authService.getUserRole();
    console.log(this.userRole);
  }

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;
  }

}
