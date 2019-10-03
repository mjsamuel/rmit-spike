import React from 'react';
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme';
import ThreadComponent from '../ThreadComponent';
import MockAdapter from 'axios-mock-adapter';
import axios from '../../axios.js'
import { DATA_API_URL } from '../../Constants'

describe('ThreadComponent', () => {
	var component, thread;

	beforeAll(() => {
		var mock = new MockAdapter(axios.instance)
		const thread_id = 1;
		const channel_id = 1;
		const user_id = 1;

		const threadServiceData = {
			title: "The Rise and Fall of SEPT at RMIT",
			content: "What is even going on",
			timeDelta: "2 minutes ago",
			// primary_channel: "sept",
			// tagged_channels: [1, 2, 3],
			channelId: channel_id,
			authorId: user_id
		}
		const userServiceData = {
			username: "smithj"
		}
		const channelServiceData = {
			channelName: "sept"
		}
		const commentServiceData = [
			{
				id: 1,
				datetime: "2019-09-16T13:42:32.000+0000",
				authorId: 0,
				upspikes: 3,
				downspikes: 1,
				content: "This is a more recent post!",
				replyId: 0,
				threadId: 1,
				archived: false,
				spikes: 2,
				spikeRatio: 0.75,
				timeDelta: "2 weeks ago"
			},
			{
				id: 2,
				datetime: "2019-09-16T18:42:32.000+0000",
				authorId: 0,
				upspikes: 30,
				downspikes: 14,
				content: "This is an even more recent post!",
				replyId: 0,
				threadId: 1,
				archived: false,
				spikes: 16,
				spikeRatio: 0.6818182,
				timeDelta: "2 weeks ago"
			},
			{
				id: 3,
				datetime: "2019-09-30T00:57:56.000+0000",
				authorId: 1,
				upspikes: 0,
				downspikes: 0,
				content: "This is a test comment",
				replyId: null,
				threadId: 1,
				archived: false,
				spikes: 0,
				spikeRatio: 1.0,
				timeDelta: "3 days ago"
			}
		]

		const match = { params: { id: 1 }}

		mock.onGet(`${DATA_API_URL}/channel/${channel_id}?user_id=${user_id}`).reply(200, channelServiceData)
		mock.onGet(`${DATA_API_URL}/thread/${thread_id}`).reply(200, threadServiceData)
		mock.onGet(`${DATA_API_URL}/user/${user_id}`).reply(200, userServiceData)
		mock.onGet(`${DATA_API_URL}/thread/${thread_id}/comment`).reply(200, commentServiceData)
		component = mount(<ThreadComponent match={match}/>)

	})

	it('should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().match.params.id).toEqual(1)
	})

	it('display primary channel when props are passed', () => {
		let channel = component.find('.channel');
		expect(channel.text()).toEqual("c/" + "sept");
	})

	it('display thread title when props are passed', () => {
		let title = component.find('.thread-title');
		expect(title.text()).toEqual("The Rise and Fall of SEPT at RMIT");
	})

	it('Author text is rendered from API call', () => {
		let author = component.find('.thread-author');
		expect(author.text()).toEqual("Posted by u/smithj 2 minutes ago");
	})


	it('Thread contents renders', () => {
		let threadContent = component.find('.thread-contents');
		expect(threadContent.text()).toEqual("What is even going on");
	})	
	/**
	 * Functionality not implemented yet; Error checking
	 */
	it('display error when props not passed', () => {
		// component = mount(<ThreadComponent />)
		// let node = component.find('.error');
		// expect(node.text()).toEqual('Sorry. Thread doesn\'t exist.');
	})

})