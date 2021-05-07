import { IRole } from "./IRole";

export default interface IUser {
    id?: Number;
    username: String;
    email: String;
    firstName?: String;
    lastName?: String;
    roles?: IRole[];
    role?: String; 
}