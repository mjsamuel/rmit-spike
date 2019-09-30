import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import SearchResultsComponent from '../SearchResultsComponent.jsx'

describe('SearchResultsComponent', () => {
	var component, searchResults;

	beforeEach(() => {
		searchResults = {
			users: [
				{
					username: "johnDoe",
					userId: "001"
				},
				{
					username: "janeDoe",
					userId: "002"
				}
			],
			channels: [
				{
					channelName: "sept",
					channelId: "001"
				},

			]
		}
	})

	beforeEach(() => {
		component = mount(<SearchResultsComponent query="test-search"/>)
	})

	it('should exist', async() => {
		await component.update();
		expect(component).toBeTruthy();
		expect(component.state('users')).toEqual(searchResults.users);
		expect(component.state('channels')).toEqual(searchResults.channels);
	})

	it('should render users properly', async() => {
		await component.update();
		let user1 = component.find("#search-username-0");
		let user2 = component.find("#search-username-1");
		expect(user1.text()).toEqual("u/" + searchResults.users[0].username);
		expect(user2.text()).toEqual("u/" + searchResults.users[1].username);
	})

	it('should render channels properly', async() => {
		await component.update();
		let channel1 = component.find("#search-channel-0");
		expect(channel1.text()).toEqual("c/" + searchResults.channels[0].channelName);
	})
})
