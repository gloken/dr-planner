import React from 'react';
import PropTypes from 'prop-types';
import RaisedButton from 'material-ui/RaisedButton';

import Menu from './Menu';

// eslint-disable-next-line semi,no-unused-vars
const App = (props) => {
  const { buttonText, onClick } = props;
  return (
    <div>
      <Menu />
      <RaisedButton
        label={buttonText}
        onClick={onClick}
      />
    </div>
  );
};

App.propTypes = {
  buttonText: PropTypes.string,
  onClick: PropTypes.func,
};
App.defaultProps = {
  buttonText: 'Trykk her bitte!',
  onClick: () => console.log('default click action'),
};

export default App;
