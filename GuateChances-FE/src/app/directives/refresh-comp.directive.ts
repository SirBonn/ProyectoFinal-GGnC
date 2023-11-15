import {
  Directive,
  Input,
  OnChanges,
  SimpleChanges,
  TemplateRef,
  ViewContainerRef,
} from '@angular/core';

@Directive({
  selector: '[appRefreshComp]',
})
export class RefreshCompDirective implements OnChanges {
  @Input() appRefreshComp!: any;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['appRefreshComp']) {
      this.viewContainer.clear();
      this.viewContainer.createEmbeddedView(this.templateRef);
    }
  }

}
