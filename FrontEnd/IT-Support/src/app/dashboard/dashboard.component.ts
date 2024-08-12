import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  sidebarOpen = true;
  userRole!: string;

  ngOnInit() {
    // Get the user role from your authentication service
    this.userRole = 'admin'; // This should be dynamic based on the logged-in user
  }


  toggleSidebar(){
    this.sidebarOpen = this.sidebarOpen? false:true
  }


}
