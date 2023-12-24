function ytdlp()
  local cmd = "/home/wayne/Develop/vscode/tensor_tutorial/venv/bin/python3 /home/wayne/Develop/vscode/tensor_tutorial/venv/bin/yt-dlp -f 'best[height<=720]'  --get-url "..vlc.access.."://"..vlc.path
  cmd = cmd.." | head -n 1"
  vlc.msg.dbg( cmd )
  local f = assert(io.popen(cmd, 'r'))
  local s = assert(f:read('*a'))
  f:close()
  if false then return s end
  s = string.gsub(s, '^%s+', '')
  s = string.gsub(s, '%s+$', '')
  s = string.gsub(s, '[\n\r]+', '')
  return s
end

function probe()
    return ( ( vlc.access == "http" or vlc.access == "https" ) and (
            ((
               string.match( vlc.path, "^www%.youtube%.com/" )
            or string.match( vlc.path, "^music%.youtube%.com/" )
            or string.match( vlc.path, "^gaming%.youtube%.com/" ) -- out of use
             ) and (
               string.match( vlc.path, "/watch%?" ) -- the html page
            or string.match( vlc.path, "/live$" ) -- user live stream html page
            or string.match( vlc.path, "/live%?" ) -- user live stream html page
            or string.match( vlc.path, "/shorts/" ) -- YouTube Shorts HTML page
            or string.match( vlc.path, "/get_video_info%?" ) -- info API
            or string.match( vlc.path, "/v/" ) -- video in swf player
            or string.match( vlc.path, "/embed/" ) -- embedded player iframe
             )) or
               string.match( vlc.path, "^consent%.youtube%.com/" )
         ) )
end

function parse()
    local ret_url=ytdlp()
    vlc.msg.dbg(ret_url)
    return { { path=ret_url, title=vlc.path } }
end