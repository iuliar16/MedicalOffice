import './App.css';
import Login from './login/login';
import { BrowserRouter as Routes, Route, Redirect } from 'react-router-dom';
import Auth from './auth/auth';
import HomePage from './homePage/homePage';
import DatePersonale from './date-personale/date-personale';
import IstoricMedical from './istoric-medical/istoric-medical';
import ListaMedici from './lista-medici/lista-medici';
import SolicitaProgramare from './solicita-programare/solicita-programare';
import ListaProgramari from './lista-programari/lista-programari';
import ListaPacienti from './lista-pacienti/lista-pacienti';
import AdaugaConsultatie from './consultatii/adauga-consultatie';
import ListaConsultatii from './consultatii/lista-consultatii';
import UpdateConsultatie from './consultatii/update-consultatie';
import AdaugaDoctor from './adauga-doctor/adauga-doctor';

function App() {
  return (
    <div>
    <Routes>
    <Route exact path="/" render={() => <Redirect to="/login" />} />
        <Route exact path="/login" component={Login} />
        <Route path="/auth" component={Auth} />
        <Route path="/home" component={HomePage} />
      <Route path="/date-personale" component={DatePersonale} />
      <Route path="/istoric-medical" component={IstoricMedical} />
      <Route path="/lista-medici" component={ListaMedici} />
      <Route path="/solicita-programare" component={SolicitaProgramare} />
      <Route path="/lista-programari" component={ListaProgramari} />
      <Route path="/lista-pacienti" component={ListaPacienti} />
      <Route path="/lista-programari/:cnp" component={ListaProgramari} />
      <Route path="/adauga-consultatie/:idDoctor/:idPacient" component={AdaugaConsultatie} />
      <Route path="/lista-consultatii/:idDoctor/:idPacient" component={ListaConsultatii} />
      <Route path="/update-consultatie/:consultatieId" component={UpdateConsultatie} />
      <Route path="/adauga-doctor" component={AdaugaDoctor} />
    </Routes>
    </div>
  );
}


export default App;
