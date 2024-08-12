import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaultComponent } from './fault.component';

describe('FaultComponent', () => {
  let component: FaultComponent;
  let fixture: ComponentFixture<FaultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FaultComponent]
    });
    fixture = TestBed.createComponent(FaultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
