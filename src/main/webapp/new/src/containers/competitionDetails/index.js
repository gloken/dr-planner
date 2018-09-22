import React from 'react';
import moment from 'moment';

export class CompetitionDetailsForm extends React.Component {
  constructor(props) {
    super(props);
    this.dateFormat = 'DD.MM.YYYY';

    this.state = {
      competitionDate: moment().format(this.dateFormat),
    };
  }

  render() {
    const { competitionDate } = this.state;
    return (
      <div>
        {competitionDate}
        Yo!
      </div>
    );
  }
}

export default CompetitionDetailsForm;
