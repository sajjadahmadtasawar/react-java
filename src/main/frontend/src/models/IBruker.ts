import { IRole } from "./IRolle";

export default interface IBruker {
    id?: Number;
    brukernavn: String;
    navn?: String;
    epost: String;
    roller?: IRole[];
}