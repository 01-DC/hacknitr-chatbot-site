import logo from './logo.svg';
import './App.css';
import ChatBot from 'react-simple-chatbot';

const steps = [
  {
    id: '0',
    message: 'Welcome to react chatbot!',
    trigger: '1',
  },
  {
    id: '1',
    message: 'Bye!',
    end: true,
  },
];

function App() {
  return (
    <ChatBot steps= {steps} />
  );
}

export default App;