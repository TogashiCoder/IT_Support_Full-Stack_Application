import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { AfterViewInit, Component, ViewChild, ViewChildren } from '@angular/core';
import { User } from 'src/app/models/user.model';
import {UserService} from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements AfterViewInit {

users:User[]=[];
dataSource = new MatTableDataSource<User>([]);
displayColumns = ["username", "email","action"];
selection = new SelectionModel<User>(true, []);

constructor(private userService:UserService){}

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit() {
    this.loadUsers();
  }


  loadUsers(){
    this.userService.getUsers().subscribe(
      (data: User[])=>{
        this.users =data;
        this.dataSource.data =this.users;
      },
      (error)=> console.log('Error fetching users:',error)
    );
  }

  selectHandler(row: User) {
    this.selection.toggle(row);
  }

}
