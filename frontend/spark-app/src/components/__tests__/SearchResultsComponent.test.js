import React from 'react';
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme';
import SearchResultsComponent from '../SearchResultsComponent';
import MockAdapter from 'axios-mock-adapter';
import axios from '../../axios.js'
import { DATA_API_URL } from '../../Constants'

describe('SearchResultsComponent', () => {
	var component, searchResults;

	beforeAll(() => {
		var mock = new MockAdapter(axios.instance)

		searchResults = {
			channels:
				[
		  		{
		    		id: 1,
		      	name: "SEPT",
		    	}
		   	],
		    users:
					[
		    		{
		        	id: 1,
		          username: "sept",
		          firstName: "john",
		          lastName: "doe",
		          upspikes: 20
		        }
		      ]
		    }

		const searchQuery = "sept";

		mock.onGet(`${DATA_API_URL}/search?query=${searchQuery}`).reply(200, searchResults)

		component = mount(<SearchResultsComponent query={searchQuery}/>)

	})

	it('should exist', async() => {
		await component.update();
		expect(component).toBeTruthy();
		expect(component.state('users')).toEqual(searchResults.users);
		expect(component.state('channels')).toEqual(searchResults.channels);
	})

	it('should render users properly', () => {
		let user1 = component.find("#search-username-0");
		let user2 = component.find("#search-username-1");
		expect(user1.text()).toEqual("u/" + searchResults.users[0].username);
	})

	it('should render channels properly', async() => {
		await component.update();
		let channel1 = component.find("#search-channel-0");
		expect(channel1.text()).toEqual("c/" + searchResults.channels[0].name);
	})
})
