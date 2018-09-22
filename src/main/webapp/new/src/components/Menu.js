import React from 'react';
import PropTypes from 'prop-types';
import AppBar from 'material-ui/AppBar';
import FlatButton from 'material-ui/FlatButton';

const Menu = (props) => {
  const { goHome } = props;
  return (
    <AppBar
      title="Our project"
      onTitleClick={goHome}
      iconElementRight={<FlatButton label="Show stuff" />}
      onRightIconButtonClick={goHome}
    />
  );
};
Menu.propTypes = {
  goHome: PropTypes.func,
};
Menu.defaultProps = {
  goHome: () => console.log('going home'),
};
export default Menu;
