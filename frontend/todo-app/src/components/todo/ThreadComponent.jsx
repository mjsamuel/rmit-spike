import React, { Component } from 'react'
import './ThreadComponent.css';
import { FaShareAlt, FaRegComment, FaFlag, FaAngleUp, FaAngleDown } from 'react-icons/fa';
import ThreadDataService from '../../api/todo/ThreadDataService.js'
import CommentComponent from './CommentComponent.jsx'


// import Icon from 'react-native-vector-icons/FontAwesome';
// import { Text, StyleSheet } from 'react-native';

// const shareButton = (
//   <Icon.Button
//     name="share"
//     backgroundColor="#3b5998"
//     onPress={this.share}
//   >
//     Share
//   </Icon.Button>
// );

// const shareButton = (
//   <Icon.Button name="share" backgroundColor="#3b5998">
//     <Text style={{ fontFamily: 'Arial', fontSize: 15 }}>
//       Share
//     </Text>
//   </Icon.Button>
// );

class ThreadComponent extends Component {

	constructor(props){
		super(props)
		console.log(props)
		this.state = {
			tagged_channels: [],
			content: '',
			comments: [],
		}
	}

	componentDidMount() {
		this.populate(this.props.id);
	}

	populate(id) {
		var thread = ThreadDataService.retrieveThread(id)
		this.setState({ 
			content: thread.content,
			tagged_channels: thread.tagged_channels,
			comments: thread.comments 
		})
	}

	submitComment(event) {
		console.log("Comment Submitted")
		// CommentDataService.submitComment()
	}
        	
    render() {
        return (
            <>
            	<div className="thread">
	            	<div className="thread-title">
	                	<h1>{this.props.title}</h1>
	                </div>
	                <div className="channel">
	                	<h2>{this.props.primary_channel}</h2>
	                </div>
	                <div className="username">
	                	<h3>{this.props.author}</h3>
	                </div>
	                <div className="thread-contents">
	                    <p>
	                    	{this.state.content}
						</p>
	                </div>
	                <div className="interactions">
	                	<button className="up-spike" onClick={this.addUpSpike}> <FaAngleUp/> </button>
                		<button className="down-spike" onClick={this.addDownSpike}> <FaAngleDown/> </button>
                		<div className="divider"/>	                	
	                	<span className="comment-interaction"> <FaRegComment/> {this.state.comments.length} Comments </span>
                		<div className="divider"/>                
                		<button className="share-interaction" onClick={this.share}> <FaShareAlt/> Share </button>
                		<div className="divider"/>                		
                		<button className="report-interaction" onClick={this.report}> <FaFlag/> Report </button>
	                </div>
	                <div className="add-comment">
	                	<form onSubmit={this.submitComment}>
	                		<textarea rows="4" placeholder="What are your thoughts?"></textarea>
	                		<input type="submit" className="btn btn-success" value="Submit" />
	                	</form>
	                </div>
	                <div className="comments">
	                	{this.state.comments.map((comment) => ( <CommentComponent {...comment} />))}
	                </div>
	            </div>
            </>
        )
    }
}

export default ThreadComponent