import IUser from "models/IUser";
import { DefaultUser } from "models/types";

// Helper
const StringIsNumber = (value: any) => isNaN(Number(value)) === true;

export const ToArray = (enumme: any) => {
  return Object.keys(enumme)
    .filter(StringIsNumber)
    .map((key) => enumme[key]);
};

export const parseJwt = (token: string) => {
  if (!token) { return; }
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(window.atob(base64));
}

export const hasAdminRole = () => {
  const user = localStorage.user;
  const token = user?.accessToken;
  const parsedToken = parseJwt(token);

  if(user & token && parsedToken && parsedToken.role === 1) {
    return true;
  }

  return false;
}

export const getCurrentUserInfo = () => {
  const user = localStorage.user;
  const token = user?.accessToken;

  const parsedToken = parseJwt(token);

  if(user & token && parsedToken) {
    const user: IUser = {
      username: parsedToken.username,
      firstName: parsedToken.firstName,
      lastName: parsedToken.lastName,
      role: parsedToken.role,
      email: parsedToken.email
    }

    return user;
  }

  return null;
}

export const daysInMonth = Array.from({ length: 31 }, (v, k) => k + 1);
export const daysInWeek = [
    {
      id: 1,
      day: "Monday",
    },
    {
      id: 2,
      day: "Tuesday",
    },
    {
      id: 3,
      day: "Wednesday",
    },
    {
      id: 4,
      day: "Thursday",
    },
    {
      id: 5,
      day: "Friday",
    },
    {
      id: 6,
      day: "Saturday",
    },
    {
      id: 7,
      day: "Sunday",
    },
  ];

const x = 30; //minutes interval
const times = []; // time array
let tt = 0; // start time
const ap = ['00', '00']; // AM-PM

//loop to increment the time and push results in array
for (var i=0;tt<24*60; i++) {
  var hh = Math.floor(tt/60); // getting hours of day in 0-24 format
  var mm = (tt%60); // getting minutes of the hour in 0-55 format
  times[i] = ("0" + (hh % 12)).slice(-2) + ':' + ("0" + mm).slice(-2) + ":" + ap[Math.floor(hh/12)]; // pushing data in array in [00:00 - 12:00 AM/PM format]
  tt = tt + x;
}

export const timePicker = times;