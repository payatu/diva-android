/*
 * DIVA Android - Damn Insecure and Vulnerable App for Android
 *
 * Copyright 2016 © Payatu
 * Author: Aseem Jakhar aseem[at]payatu[dot]com
 * Websites: www.payatu.com  www.nullcon.net  www.hardwear.io www.null.co.in
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

#include <jni.h>
#include <string.h>
#include "divajni.h"

#define VENDORKEY   "olsdfgad;lh"
#define CODE        ".dotdot"
#define CODESIZEMAX 20
/*
 * Verify the key for access
 *
 * @param  jkey   The key input by user
 *
 * @return 1 if jkey is valid, 0 otherwise. In other words 
 @         if the user key matches our key return 1, else return 0.
 */
JNIEXPORT jint JNICALL Java_jakhar_aseem_diva_DivaJni_access(JNIEnv * env, jobject jobj, jstring jkey) {

    const char * key = (*env)->GetStringUTFChars(env, jkey, 0);
    
    return ((strncmp(VENDORKEY, key, strlen(VENDORKEY)))?0:1);
}

/*
 * Launch the Friggin Nuke!
 *
 * @param jcode [IN] Launch code for the nuke
 *
 * @return 1 if successfully launched, 0 otherwise
 */
JNIEXPORT jint JNICALL Java_jakhar_aseem_diva_DivaJni_initiateLaunchSequence(JNIEnv * env, jobject jobj, jstring jcode) {

    const char * pcode = (*env)->GetStringUTFChars(env, jcode, 0);

    int ret = 0;
    char code[CODESIZEMAX];

    strcpy(code, pcode);

    // Replace the first character, if it is '!' to '.' because the boss said so ;)
    if (code[0] == '!') {
        code[0] = '.';
    }
    ret = strncmp(CODE, code, sizeof(CODE) - 1);
    if (ret == 0) {
        // Correct code entered
        // Launching in T - 10....
        ret = 1;
    }
    else {
        ret = 0;
        // Incorrect code. Access Denied! 
    }
    return ret;
}

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    return JNI_VERSION_1_6;
}
