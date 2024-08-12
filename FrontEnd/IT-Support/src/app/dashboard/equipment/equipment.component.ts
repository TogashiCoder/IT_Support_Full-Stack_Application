import { AfterViewInit, Component, ViewChild, ViewChildren } from '@angular/core';
import {Equipment} from 'src/app/models/equipment';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator'
import { MatSort } from '@angular/material/sort';

import {EquipmentService} from 'src/app/services/equipment.service';


@Component({
  selector: 'app-equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.scss']
})
export class EquipmentComponent implements AfterViewInit{

  equipments:Equipment[]=[];
  dataSource = new MatTableDataSource<Equipment>([]);
  displayColumns = ["id", "equipmentName", "purchaseDate", "assetValue", "serialNumber","action"];
  selection = new SelectionModel<Equipment>(true, []);

  constructor(private equipmentService:EquipmentService){}



  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit() {
    this.loadEquipements();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  loadEquipements() {
    this.equipmentService.getEquipments().subscribe(
      (data: Equipment[]) => {
        this.equipments = data;
        this.dataSource.data = this.equipments;
      },
      (error) => console.error('Error fetching equipments:', error)
    );
  }

  selectHandler(row: Equipment) {
    this.selection.toggle(row);
  }


}
