export const types = {
  INIT_COMP: 'INIT_COMP',
};

export const actions = {
  initializeCompetition = (date, name, location) => ({
    type: types.INIT_COMP,
    date,
    name,
    location,
  }),
};
