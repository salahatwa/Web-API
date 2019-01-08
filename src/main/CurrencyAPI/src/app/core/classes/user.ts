export class User {
  id: string = '';
  username: String = '';
  email: string = ' ';
  oldpassword: string = '';
  newpassword?: string = '';
  password?: string = '';
  phone:string='';
  city:string='';
  state:string='';
  website:string='';
  address:string='';
}

export interface AuthenticationResponse
{
  token:string;
  authorities:string[];
}
