import IBruker from "models/IBruker";

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

export const erAdmin = () => {
  const bruker = localStorage.bruker;
  const token = bruker?.accessToken;
  const parsedToken = parseJwt(token);

  if(bruker & token && parsedToken && parsedToken.role === 1) {
    return true;
  }

  return false;
}

export const hentBrukerInfo = () => {
  const bruker = localStorage.bruker;
  const token = bruker?.accessToken;

  const parsedToken = parseJwt(token);

  if(bruker & token && parsedToken) {
    const bruker: IBruker = {
      brukernavn: parsedToken.brukernavn,
      navn: parsedToken.navn,
      roller: parsedToken.roller,
      epost: parsedToken.epost
    }

    return bruker;
  }

  return null;
}

export const daysInMonth = Array.from({ length: 31 }, (v, k) => k + 1);
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