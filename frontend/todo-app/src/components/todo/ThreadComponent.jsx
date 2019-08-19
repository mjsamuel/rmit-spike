import React, { Component } from 'react'
import './ThreadComponent.css';
import { FaShareAlt, FaRegComment, FaFlag } from 'react-icons/fa';


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
    render() {
        return (
            <>
            	<div className="thread">
	            	<div className="thread-title">
	                	<h1>Thread Title</h1>
	                </div>
	                <div className="channel">
	                	<h2>rmit/sept</h2>
	                </div>
	                <div className="username">
	                	<h3>John Smith s1234567</h3>
	                </div>
	                <div className="thread-contents">
	                    <p>
	                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
	                    aliqua. Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh. Integer quis auctor
	                    elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales. Pulvinar sapien et ligula
	                    ullamcorper malesuada. Mi in nulla posuere sollicitudin aliquam. Auctor augue mauris augue neque gravida in fermentum et
	                    sollicitudin. Tincidunt nunc pulvinar sapien et. Libero id faucibus nisl tincidunt eget nullam non. Dictum sit amet
	                    justo donec enim diam vulputate. Sodales ut etiam sit amet nisl purus in mollis nunc. Vulputate mi sit amet mauris commodo.
	                    Diam vulputate ut pharetra sit amet aliquam id.
	                    </p>

						<p>
						Etiam non quam lacus suspendisse faucibus interdum posuere. Dolor morbi non arcu risus. Vulputate enim nulla aliquet
						porttitor lacus. Malesuada fames ac turpis egestas sed tempus. Tincidunt lobortis feugiat vivamus at. Enim neque volutpat
						ac tincidunt vitae semper quis. Vestibulum lorem sed risus ultricies tristique. Quis imperdiet massa tincidunt nunc pulvinar
						sapien et ligula. Senectus et netus et malesuada fames ac turpis egestas. Faucibus scelerisque eleifend donec pretium
						vulputate sapien nec sagittis aliquam. Mauris nunc congue nisi vitae suscipit tellus mauris a. Duis ut diam quam nulla
						porttitor. Viverra aliquet eget sit amet tellus cras. Tincidunt augue interdum velit euismod in pellentesque massa.
						Quam vulputate dignissim suspendisse in est.
						</p>
	                </div>
	                <div className="interactions">
	                	<span className="comment-interaction"> <FaRegComment/> 3 Comments </span>
	                	<span className="share-interaction"> <FaShareAlt/> Share </span>
	                	<span className="report-interaction"> <FaFlag/> Report </span>
	                	<span className="up-spike"> </span>
	                	<span className="up-spike"> </span>
	                </div>
	                <div className="add-comment">
	                	<form>
	                		<textarea rows="4" placeholder="What are your thoughts?"></textarea>
	                		<input type="submit" value="Submit" />
	                	</form>
	                </div>
	                <div className="comments">

	                </div>
	            </div>
            </>
        )
    }
}

export default ThreadComponent