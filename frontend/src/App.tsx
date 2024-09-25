import './App.css';
import { LambdaController } from './app/controller/LambdaController';

const AppView = LambdaController.newInstance().getView();

function App() {
  return (
    <div className="App">
      <AppView />
    </div>
  );
}

export default App;
