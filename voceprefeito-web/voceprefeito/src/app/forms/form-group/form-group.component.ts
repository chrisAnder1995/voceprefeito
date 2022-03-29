import { Component, ContentChild, Input, OnInit } from '@angular/core';
import { FormControlName, NgModel } from '@angular/forms';

@Component({
  selector: 'di-form-group',
  templateUrl: './form-group.component.html'
})
export class FormGroupComponent implements OnInit {

  @Input() classLength: string = ""
  @Input() label: string = ""
  @Input() errorMessage: string = ""
  @Input() withoutStyle = 'false'
  input; any

  @ContentChild(NgModel, {static : false}) model : NgModel
  @ContentChild(FormControlName, {static : false}) control: FormControlName

  constructor() { }

  ngOnInit() {
  }

  ngAfterContentInit(){
    this.input = this.model || this.control
    if (this.input === undefined){
      throw new Error("NGModel Directive not found or FormControlName")
    }
  }

  asterisk(){
    if (this.input.valid || !this.input.enabled){
      return ""
    } else{
      return " *"
    }
  }

  hasError() : boolean{
    //if (this.withoutStyle == true){
    //  return false
    //}
    return (this.input.invalid && this.input._parent.submitted == true)
  }

  hasSuccess(): boolean {
    if (this.withoutStyle == 'true'){
      return false
    } else{
      return (this.input.invalid && this.input._parent.submitted == true)
    }
  }
}
