export interface MenuUser {
    user_id: number,
    authority_id: number,
    id_menu: number,
    menu_name: string,
    submenus?: SubMenuItem[];
}
export interface SubMenuItem{
    id_sub_menu: number,
    sub_menu_name: string,
    url_menu: string
}

export interface MenuItem {
    user_id: number,
    user_name: string,
    authority_id: number,
    authority_name: string,
    id_menu: number,
    menu_name: string,
    id_sub_menu: number,
    sub_menu_name: string
}
