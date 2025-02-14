import { locales } from "./locales-optica";
import { motivos } from "./motivos-optica";

export interface RequestCitas{
    id:number,
    codigo:string,
    documento:string,
    nombres:string,
    email:string,
    celular:string,
    estado:boolean,
    locales: locales,
    motivo:motivos,
    observaciones:string,
    horacita:string,
    fechacita:string,
    creador?:string
}

export interface citas{
    id:number,
    codigo:string,
    documento:string,
    nombres:string,
    email:string,
    celular:string,
    estado:boolean,
    locales: locales,
    motivo:motivos,
    observaciones:string,
    horacita:string,
    fechacita:string,
    creador?:string
}