import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  rtlTitle: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: "/content/files",
    title: "ARCHIVOS",
    rtlTitle: "",
    icon: "icon-paper",
    class: ""
  },{
    path:"/content/usuarios",
    title:"USUARIOS",
    rtlTitle:"",
    icon:"icon-laptop",
    class:""
  },{
    path:"/content/servidor",
    title:"SERVIDOR",
    rtlTitle:"",
    icon:"icon-settings-gear-63",
    class:""
  }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  menuItems: any[]=[];

  constructor() {}

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }

}
