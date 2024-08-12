import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent {
id!:number;

constructor(private userService:UserService,
   private activatedRoute:ActivatedRoute,
  private router:Router){}



  ngOnInit(): void {
    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get("id") || '');
    if (this.id !=null)
      {
       this.userService.deleteUser(this.id).subscribe((data) => {
          console.log("Delete the equipmenet");
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
       });
     }
  }

}
