import { GET_PROJECT, GET_PROJECTS } from "../actions/types";

const initialState = {
  projects: [], //Array of projects
  project: {}, //Single projects
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload,
      };
    case GET_PROJECT:
      return {
        ...state,
        project: action.payload,
      };
    default:
      return state;
  }
}
