import {Link,useLocation} from 'react-router-dom';


const Menu = ({menuItems}) => {
  const location = useLocation();
  const data =location.state;
  return (
    <div>
      <nav>
      <ul>
        {data.map((item,index) =>(
          <li key={index}>
            <Link to ={item.resourceurl}>{item.resourcename}{index +1}</Link>
          </li>
        ))}
        </ul>
      </nav>
  </div>

  );
};

export default Menu;
