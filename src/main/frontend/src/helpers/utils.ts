import IBruker from "models/IBruker";
import { forIn } from "lodash";

// Helper
const StringIsNumber = (value: any) => isNaN(Number(value)) === true;

export const ToArray = (enumme: any) => {
  return Object.keys(enumme)
    .filter(StringIsNumber)
    .map((key) => enumme[key]);
};

export const EnumArray = (enumme: any) => {
  const items: any = [];
  forIn(enumme, (value, key) => items.push({key: key, value: value}));

  return items;
};


export const genererSok = (objekt: any) => {
  let sokParam = "?";
  for (const [key, value] of Object.entries(objekt)) {
    sokParam += `${key}=${value}&`
  }

  return sokParam.slice(0, -1);
}

export const parseJwt = (token: string) => {
  if (!token) { return; }
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(window.atob(base64));
}

export const erAdmin = () => {
  const token = localStorage.token;
  const parsedToken = parseJwt(token);

  if(token && parsedToken && parsedToken.bruker.erAdmin) {
    return true;
  }

  return false;
}

export const hentBrukerInfo = () => {
  const token = localStorage.token;
  const parsedToken = parseJwt(token);

  if(token && parsedToken) {
    const bruker: IBruker = {
      brukernavn: parsedToken.bruker.brukernavn,
      navn: parsedToken.bruker.navn,
      roller: parsedToken.bruker.roller,
      epost: parsedToken.bruker.epost
    }

    localStorage.bruker = bruker;

    return bruker;
  }

  return null;
}

export const daysInMonth = Array.from({ length: 31 }, (v, k) => k + 1);

export const genererAarene = () => {
  var maks = new Date().getFullYear();
  var min = maks - 5;
  var aarene = [];

  for (var i = maks; i >= min; i--) {
    aarene.push(i)
  }

  return aarene
}

export const aarene = genererAarene() || [];

export const daysInWeek = [
    {
      id: 1,
      day: "mandag",
    },
    {
      id: 2,
      day: "tirsdag",
    },
    {
      id: 3,
      day: "onsdag",
    },
    {
      id: 4,
      day: "torsdag",
    },
    {
      id: 5,
      day: "fridag",
    },
    {
      id: 6,
      day: "lørdag",
    },
    {
      id: 7,
      day: "søndag",
    },
  ];

const x = 30;
const times = []; 
let tt = 0; 
const ap = ['00', '00']; 

for (var i=0;tt<24*60; i++) {
  var hh = Math.floor(tt/60); 
  var mm = (tt%60); 
  times[i] = ("0" + (hh % 12)).slice(-2) + ':' + ("0" + mm).slice(-2) + ":" + ap[Math.floor(hh/12)];
  tt = tt + x;
}

export const timePicker = times;