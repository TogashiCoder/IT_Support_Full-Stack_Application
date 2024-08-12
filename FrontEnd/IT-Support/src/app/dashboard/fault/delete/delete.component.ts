import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FaultService } from 'src/app/services/fault.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent {

  id!: number;

  constructor(
    private faultService: FaultService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get('id') || '', 10);
    if (this.id != null) {
      this.faultService.deleteFault(this.id).subscribe(() => {
        console.log('Fault deleted successfully');
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      });
    }
  }

}
