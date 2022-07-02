import logo from './logo.svg';
import './App.css';
import Navbar from './components/navbar/Navbar.js';
import Card from './components/card/Card.js';
import FunButtonOne from './components/fun_buttons/FunButtonOne';
import StripedRowOne from "./components/striped_rows/StripedRowOne";
import FunButtonTwo from "./components/fun_buttons/FunButtonTwo";

function App() {
  return (
    <div className="App">
        <Navbar/>
        <div className={"flex-row col-sm-12 justify-content-center"}>
            <Card title={"Some Different Timezones"} text={"See these different timezones"}/>
        </div>
        <div className={"row justify-content-center"}>
            <FunButtonOne title={"Click Me!"}/>
            <FunButtonTwo title={"Click Me!"}/>
        </div>

        <StripedRowOne/>

    </div>
  );
}

export default App;
