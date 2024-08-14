import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { FaultService } from 'src/app/services/fault.service';
import { EquipmentService } from 'src/app/services/equipment.service';
import { TicketService } from 'src/app/services/ticket.service';
import { Observable } from 'rxjs';
import { startWith, map, debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Fault } from 'src/app/models/fault.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-ticket',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
  ticketForm!: FormGroup;
  equipments: any[] = [];
  filteredFaults!: Observable<Fault[]>;

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService,
    private equipmentService: EquipmentService,
    private faultService: FaultService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute


  ) {}

  ngOnInit() {
    this.createForm();
    this.loadEquipments();
    this.setupFaultAutocomplete();
  }

  createForm() {
    this.ticketForm = this.fb.group({
      status: ['CREATED', Validators.required],
      userId: [this.authService.getId(), Validators.required],
      equipmentId: ['', Validators.required],
      faultId: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  loadEquipments() {
    this.equipmentService.getEquipments().subscribe(
      data => this.equipments = data,
      error => console.error('Error loading equipments', error)
    );
  }

  setupFaultAutocomplete() {
    this.filteredFaults = this.ticketForm.get('faultId')!.valueChanges.pipe(
      startWith(''),
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(value => this.faultService.searchFaults(value || ''))
    );
  }

  displayFaultFn(fault: Fault): string {
    return fault && fault.description ? fault.description : '';
  }

  onSubmit() {
    if (this.ticketForm.valid) {
      const formValue = this.ticketForm.value;
      if (formValue.faultId && typeof formValue.faultId === 'object') {
        formValue.faultId = formValue.faultId.id;
      }
      this.ticketService.createNewTicket(formValue).subscribe(
        response => {
          console.log('Ticket created successfully', response);
          // Navigate back
          this.router.navigate(['../tickets'], { relativeTo: this.route });

        },
        error => {
          console.error('Error creating ticket', error);
          // Handle error (e.g., show an error message)
        }
      );
    }
  }
}
