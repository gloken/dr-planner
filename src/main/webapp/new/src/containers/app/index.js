import React from 'react';
import { connect } from 'react-redux';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import CompetitionDetails from '../competitionDetails';

const CompetitionPlannerApp = () => (
  <div>
    Hallo!
    <Router>
      <Route path="/" component={CompetitionDetails} />
    </Router>
  </div>
);

const mapStateToProps = state => ({
  buttonText: state.text,
});
const mapDispatchToProps = dispatch => ({
  onClick: () => {
    dispatch({ type: 'BASIC_ACTION', text: 'new text' });
  },
});
export default connect(mapStateToProps, mapDispatchToProps)(CompetitionPlannerApp);
