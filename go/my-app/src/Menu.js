import {Link} from 'react-router-dom';


const Menu = () => {
  return (
    <div>
      <nav>
      <ul>
          <li>
            <Link to="/reservation">預約</Link>
          </li>
        </ul>
      </nav>
  </div>

  );
};

export default Menu;
